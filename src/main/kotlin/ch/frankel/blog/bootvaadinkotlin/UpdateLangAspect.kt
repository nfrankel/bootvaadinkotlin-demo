package ch.frankel.blog.bootvaadinkotlin

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.jsoup.nodes.Document

@Aspect
open class UpdateLangAspect {

    @Pointcut("execution(static * org.jsoup.nodes.Document.createShell(..))")
    fun callDocumentCreateShell(): Unit {}

    @AfterReturning(pointcut = "callDocumentCreateShell()", returning = "document")
    fun setLangAttribute(document: Document): Unit {
        document.childNode(0).attr("lang", "fr")
    }
}