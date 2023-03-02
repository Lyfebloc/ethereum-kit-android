package com.lyfebloc.ethereumkit.models

import com.lyfebloc.ethereumkit.core.toHexString

class Signature(val v: Int,
                val r: ByteArray,
                val s: ByteArray) {
    override fun toString(): String {
        return "Signature [v: $v; r: ${r.toHexString()}; s: ${s.toHexString()}]"
    }
}
