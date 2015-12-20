package ch.frankel.blog.bootvaadinkotlin

import javax.sql.*
import com.vaadin.data.util.sqlcontainer.*
import com.vaadin.data.util.sqlcontainer.connection.*
import com.vaadin.data.util.sqlcontainer.query.*
import com.vaadin.ui.*
import org.vaadin.spring.events.EventBus
import org.vaadin.spring.mvp.Presenter
import org.vaadin.spring.events.EventScope.SESSION
import org.vaadin.spring.events.annotation.EventBusListenerMethod
import ch.frankel.blog.bootvaadinkotlin.TableConstants.person
import ch.frankel.blog.bootvaadinkotlin.TableConstants.firstName
import ch.frankel.blog.bootvaadinkotlin.TableConstants.lastName
import ch.frankel.blog.bootvaadinkotlin.TableConstants.birthdate

class TablePresenter(datasource: DataSource, view: Table, eventBus: EventBus): Presenter<Table>(view, eventBus) {

    init {
        val pool = J2EEConnectionPool(datasource)
        val tableQuery = TableQuery(person, pool)
        view.containerDataSource = SQLContainer(tableQuery)
        view.setVisibleColumns(firstName, lastName, birthdate)
        view.setColumnHeader(firstName, "Given Name")
        view.setColumnHeader(lastName, "Family Name")
        view.setColumnHeader(birthdate, "Date of birth")
        view.isSelectable = true
        view.isImmediate = true
        view.addValueChangeListener {
            val rowId = it.property.value
            val rowItem = view.containerDataSource.getItem(rowId)
            eventBus.publish(SESSION, rowItem)
        }
    }

    @EventBusListenerMethod
    fun showNotification(message: RowItem) {
        val firstName = message.getItemProperty(TableConstants.firstName).value as String
        Notification.show("Hello ${firstName} from Spring Boot Vaadin in Kotlin")
    }
}

internal object TableConstants {

    val person = "PERSON"
    val firstName = "FIRST_NAME"
    val lastName = "LAST_NAME"
    val birthdate = "BIRTHDATE"
}