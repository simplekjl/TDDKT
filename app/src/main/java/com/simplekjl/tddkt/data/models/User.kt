package com.simplekjl.tddkt.data.models

import android.os.Parcel
import android.os.Parcelable


class User() : Parcelable {
    var id: Int = 0
    var name: String? = null
    var username: String? = null
    var email: String? = null
    var address: Address = Address()
    var phone: String? = null
    var website: String? = null
    var company: Company = Company()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        username = parcel.readString()
        email = parcel.readString()
        phone = parcel.readString()
        website = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}


class Company() : Parcelable {
    var name: String? = null
    var catchPhrase: String? = null
    var bs: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        catchPhrase = parcel.readString()
        bs = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(catchPhrase)
        parcel.writeString(bs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Company> {
        override fun createFromParcel(parcel: Parcel): Company {
            return Company(parcel)
        }

        override fun newArray(size: Int): Array<Company?> {
            return arrayOfNulls(size)
        }
    }
}

class Address() : Parcelable {
    var street: String? = null
    var suite: String? = null
    var city: String? = null
    var zipcode: String? = null
    var geo: Geo = Geo()

    constructor(parcel: Parcel) : this() {
        street = parcel.readString()
        suite = parcel.readString()
        city = parcel.readString()
        zipcode = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(street)
        parcel.writeString(suite)
        parcel.writeString(city)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}

class Geo() : Parcelable {
    var lat: String? = null
    var lng: String? = null

    constructor(parcel: Parcel) : this() {
        lat = parcel.readString()
        lng = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lat)
        parcel.writeString(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Geo> {
        override fun createFromParcel(parcel: Parcel): Geo {
            return Geo(parcel)
        }

        override fun newArray(size: Int): Array<Geo?> {
            return arrayOfNulls(size)
        }
    }
}