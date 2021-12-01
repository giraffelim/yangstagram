package com.giraffelim.entity

import com.giraffelim.config.AuditConfig
import com.giraffelim.repository.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.EntityNotFoundException
import javax.persistence.PersistenceContext

@DataJpaTest
@Transactional
@Import(value = [AuditConfig::class])
class UserTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @PersistenceContext
    lateinit var em: EntityManager

    @Test
    @DisplayName("Auditing 테스트")
    fun `jpa_audit_test`() {
        val user = User(username = "giraffelim", password = "pass", name = "테스터")
        userRepository.save(user)

        em.flush()
        em.clear()

        val findUser = userRepository.findById(user.id!!).orElseThrow { EntityNotFoundException() }
        Assertions.assertThat(findUser.createdDate).isNotNull
        Assertions.assertThat(findUser.modifiedDate).isNotNull
    }

}