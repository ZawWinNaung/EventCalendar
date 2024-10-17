package zawwin.naung.eventcalendar.di

import zawwin.naung.eventcalendar.data.MyEventRepo
import zawwin.naung.eventcalendar.data.MyEventRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import zawwin.naung.eventcalendar.auth.AccountService
import zawwin.naung.eventcalendar.auth.AccountServiceImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindMyEventRepo(myEventRepoImpl: MyEventRepoImpl): MyEventRepo

    @Binds
    abstract fun bindAccountService(accountServiceImpl: AccountServiceImpl): AccountService
}