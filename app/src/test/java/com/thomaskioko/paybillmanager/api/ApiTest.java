package com.thomaskioko.paybillmanager.api;


import com.thomaskioko.paybillmanager.api.models.PostPayBillData;
import com.thomaskioko.paybillmanager.api.models.ServerPaymentResponse;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;

/**
 * Class to test all endpoints.
 *
 * @author Thomas Kioko
 */
public class ApiTest extends BaseTestCase {


    @Test
    public void getPaymentResponse() throws IOException {

        PostPayBillData postPayBillData = new PostPayBillData();
        postPayBillData.setPhoneNumber(TestData.PHONE_NUMBER);
        postPayBillData.setTotalAmount(TestData.TOTAL_AMOUNT);
        postPayBillData.setClientName(TestData.CLIENT_NAMES);
        postPayBillData.setClientName(TestData.CLIENT_LOCATION);

        Call<ServerPaymentResponse> serverPaymentResponseCall = getApiClient().mpesaServices()
                .getPaymentResponse(postPayBillData);

        Response<ServerPaymentResponse> request = serverPaymentResponseCall.execute();

        assertEquals(request.code(), 200);
        assertEquals(true, request.isSuccessful());
    }
}
