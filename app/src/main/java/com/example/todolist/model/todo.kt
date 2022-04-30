package com.example.todolist.model

import android.os.Parcel
import android.os.Parcelable

class todo(
    val text: String,
    val isUrgent: Boolean,
    val isDone: Boolean
): Parcelable {

    constructor(parcel: Parcel) : this(

        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeByte(if (isUrgent) 1 else 0)
        parcel.writeByte(if (isDone) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<todo> {
        override fun createFromParcel(parcel: Parcel): todo {
            return todo(parcel)
        }

        override fun newArray(size: Int): Array<todo?> {
            return arrayOfNulls(size)
        }
    }


}