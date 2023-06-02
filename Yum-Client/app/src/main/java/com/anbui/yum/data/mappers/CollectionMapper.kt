package com.anbui.yum.data.mappers

import com.anbui.yum.data.remote.collection.CollectionDto
import com.anbui.yum.domain.model.Collection

fun CollectionDto.toCollection(): Collection =
    Collection(
        id = this.id,
        recipes = this.recipes,
        name = this.title,
        userId = this.userId,
    )
