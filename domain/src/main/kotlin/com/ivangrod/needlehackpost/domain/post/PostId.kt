package com.ivangrod.needlehackpost.domain.post

import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class PostId(val value: String?) {

    companion object{
        fun buildFromUri(uri: String): PostId {
            var identifier = ""
            try {
                val md: MessageDigest = MessageDigest.getInstance("MD5")
                md.update(uri.toByteArray(charset("UTF-8")), 0, uri.length)
                identifier = BigInteger(1, md.digest()).toString(16)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            return PostId(identifier)
        }
    }
}
