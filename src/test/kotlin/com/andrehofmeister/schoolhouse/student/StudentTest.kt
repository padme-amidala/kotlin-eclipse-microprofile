package com.andrehofmeister.schoolhouse.student

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(Arquillian::class)
internal class StudentTest {
  companion object {
    @JvmStatic
    @Deployment
    fun createDeployment(): WebArchive {
      return ShrinkWrap.create(EmbeddedGradleImporter::class.java).forThisProjectDirectory().forTasks("war").importBuildOutput()
        .`as`(WebArchive::class.java)
    }
  }

  @field:[Inject DefaultStudentRepository]
  private lateinit var studentRepository: StudentRepository

  @Test
  fun `Simple test without any purpose, except testing dependency injection`() {
    assertTrue(studentRepository.all().isEmpty())
  }
}
