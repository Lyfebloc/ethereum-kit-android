package com.lyfebloc.ethereumkit.models

import com.lyfebloc.ethereumkit.core.AddressValidator
import com.lyfebloc.ethereumkit.core.hexStringToByteArray
import com.lyfebloc.ethereumkit.core.toHexString
import com.lyfebloc.ethereumkit.utils.EIP55

data class Address(var raw: ByteArray) {
    init {
        if (raw.size == 32) {
            raw = raw.copyOfRange(12, raw.size)
        }
        AddressValidator.validate(hex)
    }

    constructor(hex: String) : this(hex.hexStringToByteArray())

    val hex: String
        get() = raw.toHexString()

    val eip55: String
        get() = EIP55.format(hex)

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        return if (other is Address)
            raw.contentEquals(other.raw)
        else false
    }

    override fun hashCode(): Int {
        return raw.contentHashCode()
    }

    override fun toString(): String {
        return hex
    }

}
