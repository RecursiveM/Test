package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName
import com.ncgr.maqsaf.domain.auth.model.UserToken

data class UserTokenDto(

    @field:SerializedName("access_token")
    val accessToken: String,

    @field:SerializedName("refresh_token")
    val refreshToken: String,

    @field:SerializedName("token_type")
    val tokenType: String,

    @field:SerializedName("expires_in")
    val expiresIn: Int,

    @field:SerializedName("user")
    val userInfo: UserInfo
) {
    fun toUserToken(): UserToken {
        return UserToken(
			token =  accessToken,
			user =  userInfo,
		)
    }
}

data class IdentityData(

    @field:SerializedName("sub")
    val sub: String
)

data class AppMetadata(

    @field:SerializedName("provider")
    val provider: String,

    @field:SerializedName("providers")
    val providers: List<String>
)

data class UserInfo(

    @field:SerializedName("aud")
    val aud: String,

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("user_metadata")
    val userMetadata: UserMetadata,

    @field:SerializedName("identities")
    val identities: List<IdentitiesItem>,

    @field:SerializedName("last_sign_in_at")
    val lastSignInAt: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("phone_confirmed_at")
    val phoneConfirmedAt: String,

    @field:SerializedName("app_metadata")
    val appMetadata: AppMetadata
)

data class UserMetadata(
    val any: Any? = null
)

data class IdentitiesItem(

    @field:SerializedName("identity_data")
    val identityData: IdentityData,

    @field:SerializedName("last_sign_in_at")
    val lastSignInAt: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("user_id")
    val userId: String,

    @field:SerializedName("provider")
    val provider: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: String
)
