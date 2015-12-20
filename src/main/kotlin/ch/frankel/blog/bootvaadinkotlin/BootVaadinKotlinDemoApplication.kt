package ch.frankel.blog.bootvaadinkotlin

import com.vaadin.annotations.*
import com.vaadin.server.*
import com.vaadin.spring.annotation.*
import com.vaadin.ui.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*

@SpringBootApplication
open class BootVaadinKotlinDemoApplication

fun main(vararg args: String) {
    SpringApplication.run(BootVaadinKotlinDemoApplication::class.java)
}

@SpringUI
@Theme("valo")
class AppUI: UI() {

    @Autowired
    private lateinit var mainScreenPresenter: MainScreenPresenter

    override fun init(request: VaadinRequest) {
        content = mainScreenPresenter.view
    }
}

