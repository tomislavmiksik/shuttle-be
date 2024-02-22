import com.tomislavmiksik.shuttle.shuttlebe.auth.domain.service.UserDetailsImpl
import com.tomislavmiksik.shuttle.shuttlebe.user.domain.entity.User
import com.tomislavmiksik.shuttle.shuttlebe.user.domain.repository.UserRepository
import lombok.AllArgsConstructor
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
@AllArgsConstructor
class UserDetailsServiceImpl : UserDetailsService {
    private val userRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User =
            userRepository?.findUserByUsername(username) ?: throw UsernameNotFoundException("User not found...")

        val encoder: PasswordEncoder = BCryptPasswordEncoder()
        return UserDetailsImpl(
            username,
            encoder.encode(user.password),
            listOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        )
    }
}