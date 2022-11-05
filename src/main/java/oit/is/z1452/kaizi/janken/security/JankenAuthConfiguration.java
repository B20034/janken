package oit.is.z1452.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class JankenAuthConfiguration {
  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    // このクラスの下部にあるPasswordEncoderを利用して，ユーザのビルダ（ログインするユーザを作成するオブジェクト）を作成する
    UserBuilder users = User.builder();

    // UserBuilder usersにユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されている．
    // ハッシュ化されたパスワードを得るには，この授業のbashターミナルで下記のように末尾にユーザ名とパスワードを指定すると良い(要VPN)
    // $ sshrun htpasswd -nbBC 10 user1 p@ss
    // ロールを複数追加することもできる
    UserDetails user1 = users
        .username("user1")
        .password("$2y$10$TFacvCCTe2LR4wDFC6idf.HsSqERCR8z7mAcltNaisFlMPH/6YWIa")
        .roles("USER", "MANAGER")
        .build();
    UserDetails user2 = users
        .username("user2")
        .password("$2y$10$XsLSQ20tMzXnIlqTed71G.WsbfR386WKOXRBTuHQ7c9J31eLnRAm.")
        .roles("USER")
        .build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2);
  }

  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin();
    http.authorizeHttpRequests()
        .mvcMatchers("/janken/**").authenticated();
    http.headers().frameOptions().disable();
    http.csrf().disable();
    http.headers().frameOptions().disable();
    return http.build();
  }

  /**
   *
   * UserBuilder users = User.builder();で利用するPasswordEncoderを指定する．
   *
   * @return BCryptPasswordEncoderを返す
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
