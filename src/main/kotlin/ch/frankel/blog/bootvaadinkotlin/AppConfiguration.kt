package ch.frankel.blog.bootvaadinkotlin

import com.vaadin.server.*
import com.vaadin.spring.annotation.*
import com.vaadin.spring.server.*
import com.vaadin.ui.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.vaadin.spring.events.internal.ScopedEventBus
import javax.sql.*

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

    @Bean
    open fun vaadinServlet() = CustomVaadinServlet({ event: SessionInitEvent ->

        event.session.addBootstrapListener(object : BootstrapListener {

            override fun modifyBootstrapFragment(response: BootstrapFragmentResponse) {
                // NOP, this is for portlets etc
            }

            override fun modifyBootstrapPage(response: BootstrapPageResponse) {
                response.document.child(0).attr("lang", "fr")
            }
        })
    })
}

class CustomVaadinServlet(private val listener: (SessionInitEvent) -> Unit) : SpringVaadinServlet() {

    override fun servletInitialized() {
        super.servletInitialized();
        service.addSessionInitListener(listener)
    }
}