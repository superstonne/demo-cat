package com.example.servicea.servicea.context;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2019-11-21 17:42
 */
@Component
public class RestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Transaction t = Cat.newTransaction(CatConstants.TYPE_REMOTE_CALL, request.getURI().toString());

        try {
            HttpHeaders headers = request.getHeaders();

            // 保存和传递CAT调用链上下文
            Cat.Context ctx = new CatContextImpl();
            Cat.logRemoteCallClient(ctx);
            headers.add(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, ctx.getProperty(Cat.Context.ROOT));
            headers.add(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, ctx.getProperty(Cat.Context.PARENT));
            headers.add(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, ctx.getProperty(Cat.Context.CHILD));

            // 保证请求继续被执行
            ClientHttpResponse response =  execution.execute(request, body);
            t.setStatus(Transaction.SUCCESS);
            return response;
        } catch (Exception e) {
            Cat.getProducer().logError(e);
            t.setStatus(e);
            throw e;
        } finally {
            t.complete();
        }
    }
}
