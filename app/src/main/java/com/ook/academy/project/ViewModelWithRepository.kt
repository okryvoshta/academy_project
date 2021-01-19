package com.ook.academy.project

import androidx.lifecycle.ViewModel

abstract class ViewModelWithRepository(val repository: Repository) : ViewModel()