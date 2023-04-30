package com.uva.kmm_template.android.detail

import com.uva.kmm_template.android.utils.StateViewModel
import com.uva.kmm_template.repository.SampleRepository

class DetailViewModel(private val sampleRepository: SampleRepository) :
    StateViewModel<DetailComponents.State, DetailComponents.Action, DetailComponents.Event>(
        initialState = DetailComponents.State()
    )
