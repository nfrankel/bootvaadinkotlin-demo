package ch.frankel.blog.bootvaadinkotlin

import javax.sql.*
import com.vaadin.spring.annotation.*
import com.vaadin.ui.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.vaadin.spring.events.internal.ScopedEventBus

@Configuration
open class AppConfiguration {

    @Autowired
    private lateinit var datasource: DataSource

    @Bean @UIScope
    open fun notificationButton() = NotificationButton()

    @Bean @UIScope
    open fun notificationButtonPresenter() = NotificationButtonPresenter(notificationButton(), appEventBus())

    @Bean @UIScope
    open fun mainScreen() = MainScreen()

    @Bean @UIScope
    open fun mainScreenPresenter() = MainScreenPresenter(tablePresenter(), notificationButtonPresenter(), mainScreen(), appEventBus())

    @Bean @UIScope
    open fun table() = Table()

    @Bean @UIScope
    open fun tablePresenter() = TablePresenter(datasource, table(), appEventBus())

    @Bean
    open fun appEventBus() = ScopedEventBus.DefaultApplicationEventBus()
}