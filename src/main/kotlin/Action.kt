sealed class Action{


    class Registration: Action() {}
    class Logout: Action(){}
    class Login(var user: Task2.User): Action(){}
}
