package com.example.mithosaanjh.model

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    var userId : String = "",
    var email : String = "",
    var firstName : String = "",
    var lastName : String = "",
    var address : String = "",
    var phoneNumber : String = "",
    var profileImageUrl : String = "",
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""

    ) {
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(address)
        parcel.writeString(phoneNumber)
        parcel.writeString(profileImageUrl)
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}