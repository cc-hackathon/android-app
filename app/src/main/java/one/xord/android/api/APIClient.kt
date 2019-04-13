package one.xord.android.api

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import one.xord.android.statics.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sami on 10/27/18.
 */

object APIClient {

    private var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return retrofit
        }

    val gson: Gson
        get() {
            return GsonBuilder()
                    .addSerializationExclusionStrategy(object : ExclusionStrategy {
                        override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                            val expose = fieldAttributes.getAnnotation(Expose::class.java)
                            return expose != null && !expose.serialize
                        }

                        override fun shouldSkipClass(aClass: Class<*>): Boolean {
                            return false
                        }
                    })
                    .addDeserializationExclusionStrategy(object : ExclusionStrategy {
                        override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                            val expose = fieldAttributes.getAnnotation(Expose::class.java)
                            return expose != null && !expose.deserialize
                        }

                        override fun shouldSkipClass(aClass: Class<*>): Boolean {
                            return false
                        }
                    })
                    .create()
        }
}
