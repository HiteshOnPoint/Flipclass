package com.ops.flipclass.di

import com.ops.flipclass.ui.activity.authorization.viewmodel.*
import com.ops.flipclass.ui.activity.authorization.viewmodel.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
    /*viewModel {
        RegisterViewModel(get())
    }
    viewModel {
        UserProfileViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        ForgetPassViewModel(get())
    }
    viewModel {
        CategoryProductViewModel(get())
    }

    viewModel {
        SearchViewModel(get())
    }

    viewModel {
        AccountViewModel(get())
    }

    viewModel {
        OrderViewModel(get())
    }

    viewModel {
        CheckoutViewModel(get())
    }

    viewModel {
        AddressViewModel(get())
    }

    viewModel {
        LogoutViewModel(get())
    }

    viewModel {
        NotificationViewModel(get())
    }

    viewModel {
        NotifyMeViewModel(get())
    }*/
}