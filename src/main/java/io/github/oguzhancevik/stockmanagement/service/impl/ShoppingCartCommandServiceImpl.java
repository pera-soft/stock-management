package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule;
import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.model.response.ApiResponse;
import io.github.oguzhancevik.stockmanagement.service.ProductQueryService;
import io.github.oguzhancevik.stockmanagement.service.ShoppingCartCommandService;
import io.github.oguzhancevik.stockmanagement.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.INVALID_TRANSACTION;

@Service
@Slf4j
public class ShoppingCartCommandServiceImpl implements ShoppingCartCommandService {

    private final ProductQueryService productQueryService;
    private final RedisTemplate redisTemplate;

    @Autowired
    public ShoppingCartCommandServiceImpl(ProductQueryService productQueryService, RedisTemplate redisTemplate) {
        this.productQueryService = productQueryService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ApiResponse shopping(ShoppingRequest request) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                Long stockAmount;
                List result;
                do {
                    final var STOCK_KEY = Constants.CACHE.STOCK_KEY + request.getProductId();
                    redisOperations.watch(STOCK_KEY);

                    stockAmount = (Long) redisOperations.opsForValue().get(STOCK_KEY);
                    redisOperations.multi();
                    if (stockAmount == null) {
                        stockAmount = productQueryService.findById(request.getProductId()).getStockAmount();
                        redisOperations.opsForValue().set(STOCK_KEY, stockAmount);
                    }

                    if (stockAmount != null && stockAmount > 0) {
                        redisOperations.opsForValue().decrement(STOCK_KEY);
                        result = redisOperations.exec();
                        log.info("result: " + result);
                    } else
                        throw new BusinessValidationException(INVALID_TRANSACTION);

                } while (result.isEmpty() && stockAmount > 0);

                if (result.isEmpty())
                    throw new BusinessValidationException(INVALID_TRANSACTION);

                return result;
            }
        });

        var response = new ApiResponse(BusinessValidationRule.SUCCESSFUL_TRANSACTION.getMessage());
        response.setSuccess(Boolean.TRUE);
        return response;
    }

}
