package com.github.nunes03.av2.entities

import android.os.Parcel
import android.os.Parcelable

class UserEntity() : Parcelable, AbstractEntity() {

    var id: Int? = null

    var name: String? = null

    var email: String? = null

    var password: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        email = parcel.readString()
        password = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun getTableName(): String {
        return "user"
    }

    companion object CREATOR : Parcelable.Creator<UserEntity> {
        override fun createFromParcel(parcel: Parcel): UserEntity {
            return UserEntity(parcel)
        }

        override fun newArray(size: Int): Array<UserEntity?> {
            return arrayOfNulls(size)
        }
    }
}
