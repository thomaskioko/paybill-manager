package com.thomaskioko.paybillmanager.api.interfaces;

import com.thomaskioko.paybillmanager.api.models.PostPayBillData;
import com.thomaskioko.paybillmanager.api.models.ServerConfirmationResponse;
import com.thomaskioko.paybillmanager.api.models.ServerPaymentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * This class contains API Endpoints
 *
 * @author Thomas Kioko
 */
public interface MpesaServicesInterface {

    /**
     * Make a payment request. For more info
     * {@see <a="http://kn9ts.github.io/project-mulla/docs/payment-request.html">}
     *
     * @param postPayBillData {@link PostPayBillData} PayBill Post Data
     * @return JSON PaymentResponse from server
     */
    @POST("/request")
    Call<ServerPaymentResponse> getPaymentResponse(@Body PostPayBillData postPayBillData);

    /**
     * Method called to confirm payment. It should be invoked one a successful payment request has
     * been done. Once  confirmed, a pop should appear on the client’s phone.
     * The client will be required to key in their BONGA POINTS PIN to complete the payment process.
     * For more info
     * {@see <a="http://kn9ts.github.io/project-mulla/docs/payment-confirm.html">}
     *
     * @param transactionId Id Generated after a successful post request
     * @return JSON Response from server
     */
    @GET("/confirm/{trx_id}")
    Call<ServerConfirmationResponse> getConfirmationResponse(@Path("trx_id") String transactionId);

    /**
     * Check the status of a payment. For more info
     * {@see <a="http://kn9ts.github.io/project-mulla/docs/payment-status.html">}
     *
     * @param transactionId Id Generated after a successful post request
     * @return JSON Response from server
     */
    @GET("/status/:{trx_id}")
    Call<ServerConfirmationResponse> getPaymentStatus(@Path("trx_id") String transactionId);
}
