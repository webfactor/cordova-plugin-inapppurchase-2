/* Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alexdisler.inapppurchases;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.android.billingclient.api.SkuDetails;

/**
 * Represents an in-app product's listing details.
 */
public class IabSkuDetails {
    String mItemType;
    String mSku;
    String mType;
    Double mPriceAsDecimal;
    String mPrice;
    String mPriceCurrency;
    String mPriceRaw;
    String mTitle;
    String mDescription;
    SkuDetails mSkuDetails;

    String TAG = "google.payments ISD";

    public IabSkuDetails(SkuDetails skuDetails) {
        this(IabHelper.ITEM_TYPE_INAPP, skuDetails);
    }

    public IabSkuDetails(String itemType, SkuDetails skuDetails) {
        mSkuDetails = skuDetails;
        mItemType = itemType;
        mSku = skuDetails.getSku();
        mType = skuDetails.getType();
        mPrice = skuDetails.getPrice();
        mPriceCurrency = skuDetails.getPriceCurrencyCode();
        mPriceAsDecimal = skuDetails.getPriceAmountMicros()/Double.valueOf(1000000);
        mTitle = skuDetails.getTitle();
        mDescription = skuDetails.getDescription();

        long priceMicros = skuDetails.getPriceAmountMicros();
        DecimalFormat formatter = new DecimalFormat("#.00####");
        formatter.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        mPriceRaw = formatter.format(priceMicros / 1000000.0);
    }

    public String getSku() { return mSku; }
    public String getType() { return mType; }
    public String getPrice() { return mPrice; }
    public String getPriceCurrency() { return mPriceCurrency; }
    public Double getPriceAsDecimal() { return mPriceAsDecimal; }
    public String getPriceRaw() { return mPriceRaw; }
    public String getTitle() { return mTitle; }
    public String getDescription() { return mDescription; }

    @Override
    public String toString() {
        return "IabSkuDetails:" + mSkuDetails;
    }
}
