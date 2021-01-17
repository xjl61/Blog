package com.littercarp.articleservice.service.impl;

import com.littercarp.articleservice.entity.Article;
import com.littercarp.articleservice.mapper.ArticleMapper;
import com.littercarp.articleservice.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * article 服务实现类
 * </p>
 *
 * @author litterCarp
 * @since 2021-01-17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
