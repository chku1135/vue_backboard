@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DisplayName("2.유저정보 검색 후 비밀번호 비교")
    @Test
    void test_2(){
//        String encPassword = passwordEncoder.encode("test_password");
//
//        UserEntity user = userRepository.findByUserId("test_user").orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//
//        assertThat(user.getUserPw()).isEqualTo(encPassword);
        String userId = "test_user";
        String userPw = "test_password";
        UserDetails user = userService.loadUserByUsername(userId);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, userPw);

        asserThat(authenticationToken.getCredentials()).isEqualTo(userPw);

        System.out.println("getCredentials: " + authenticationToken.getCredentails());
        System.out.println("userPw: " + userPw);
    }
}