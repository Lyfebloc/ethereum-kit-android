package com.lyfebloc.nftkit.core.db

import androidx.room.TypeConverter
import com.lyfebloc.nftkit.models.NftType

class NftTypeConverters {
    @TypeConverter
    fun nftTypeFromString(string: String?): NftType? {
        return string?.let { enumValueOf<NftType>(it) }
    }

    @TypeConverter
    fun nftTypeToString(nftType: NftType?): String? {
        return nftType?.name
    }
}
