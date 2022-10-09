sealed class Action{
    class Registration: Action() {}
    class Logout: Action() {}
    class Login(var user: User): Action(){}
}
