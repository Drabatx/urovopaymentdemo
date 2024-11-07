package com.test.urovopaymentapp.di

import android.content.Context
import android.os.Environment
import com.test.urovopaymentapp.data.model.models.merchant.ConfigPrefsTool
import com.test.urovopaymentapp.data.model.models.merchant.ConfigUtils
import com.test.urovopaymentapp.data.model.models.merchant.MerchantParams
import com.test.urovopaymentapp.data.model.models.merchant.MerchantPrefsTool
import com.test.urovopaymentapp.data.model.models.merchant.SharedPrefsTool
import com.test.urovopaymentapp.domain.repository.TradingRepositoryImpl
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UrovoModule {
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideEmvNfcKernelApi(): EmvNfcKernelApi {
        return EmvNfcKernelApi.getInstance()
    }

    @Provides
    @Singleton
    fun provideTradingCardRepository(context: Context): TradingRepositoryImpl {
        return TradingRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideExternalApiDir(context: Context): File {
        return File(Environment.getExternalStorageDirectory(), context.packageName)
    }

    @Provides
    @Singleton
    fun provideSharedPrefsTool(context: Context, externalApiDir: File): SharedPrefsTool {
        return SharedPrefsTool(context, externalApiDir)
    }

    @Provides
    @Singleton
    fun provideMerchantPrefsTool(sharedPrefsTool: SharedPrefsTool, configUtils: ConfigUtils):MerchantPrefsTool {
        return MerchantPrefsTool(sharedPrefsTool, configUtils)
    }

    @Provides
    @Singleton
    fun provideMerchantParams(merchantPrefsTool: MerchantPrefsTool): MerchantParams {
        return MerchantParams(merchantPrefsTool)
    }

    @Provides
    @Singleton
    fun provideConfigPrefsTool(sharedPrefsTool: SharedPrefsTool): ConfigPrefsTool{
        return ConfigPrefsTool(sharedPrefsTool)
    }

    @Provides
    @Singleton
    fun provideConfigUtils(configPrefsTool: ConfigPrefsTool): ConfigUtils {
        return ConfigUtils(configPrefsTool)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object CardReaderModule {

//    @Provides
//    fun provideMyEmvListener(
//        mKernelApi: EmvNfcKernelApi,
//        iBeeper: BeeperImpl,
//        iLed: LEDDriverImpl
//    ): MyEmvListener {
//        return MyEmvListener(mKernelApi, iBeeper, iLed)
//    }

}