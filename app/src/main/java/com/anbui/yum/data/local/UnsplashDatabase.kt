package com.anbui.yum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anbui.yum.data.local.dao.UnsplashImageDao
import com.anbui.yum.data.local.dao.UnsplashRemoteKeysDao

//@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao

}
