package com.manuelperera.topmovies20.presentation.base.recyclers

import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType.ITEM

class PagingObjectImpl : PagingObject {

    override var itemViewType: ItemViewType = ITEM

}