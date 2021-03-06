package com.sjn.stamp.db

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

open class Artist(
        @PrimaryKey var id: Long = 0,
        @Index var name: String = "",
        var albumArtUri: String = ""
) : RealmObject() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Artist) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int = name.hashCode()
}