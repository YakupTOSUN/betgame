# betgame
>  The purpose of our project is to create a system similar to a betting system.
The admin-server creates a bulletin (betround), the user makes a prediction and saves it to the system,
and then when the bulletin is resolved, if the user's coupons match the bulletin result,
the success or failure is sent to the user's email address and the database is updated.
    Our project has 3 controllers:
# AccountController
    /account/register
-> method name:  <p> register(@RequestBody RegisterRequest request) </p>
This request allows us to create a user registration in our API. It is a permitall request, open to everyone.

    /account/login
-> method name: <p>  login(@RequestBody @Valid LoginRequest request)  </p>
This request allows us to login to the API and check the token. It is a permitall request, open to everyone.

    /account/me
-> method name: <p>  getMe()  </p>
This request retrieves information about the logged-in user. It is an IsAuthentificated request, open to anyone who is logged in.

    /account/forgotpassword
-> method name:   <p>  putPassword(@RequestParam String mail)   </p>
This request generates a random password for the user with the given email address, updates the database, and sends the new password to the user by email. It is a permitall request, open to everyone.


# BetRoundController
    /betround/save/server
-> method name: saveBetRound(@RequestBody BetRoundRequest betRoundRequest)   <p>
If the admin is logged in, this request adds a bulletin to the database with the role of SERVER, marking the game list and bulletin name and other information in the request. The user must have the same serverBetRoundId as the bulletin and the serverId in the gameList must match the Id of each game in the bulletin. In fact, two operations are performed with one post request. It saves the bulletin and creates a coupon for the user. It is an IsAuthentificated request, open to anyone who is logged in.

    /bedround
-> method name: saveGameResult(@RequestBody GameResult gameResult) <p>
This patchmapping operation allows us to enter and update the match results that we left blank when creating our bulletin. It is an OnlyAdmin request, only open to admins.

    /betround/update/server
-> method name: saveBetRoundIsSuccessAndSendMail(@RequestParam Long betRoundId) <p>
This request finds the betRound with the given betRoundID and retrieves the betRounds with the same serverBetRoundId and betRole of user (coupons), then checks the results (DRAW, FIRST, SECOND) in the betRound's gameList against the coupons, updates the betStatus, and sends the results to the users' email addresses using the ownerId in the betRounds. It is an OnlyAdmin request, only open to admins.

    /betround
-> method name: getAllBetRounds(@Nullable @RequestParam PlayableStatus playableStatus, Pageable pageable)    <p>
This request lists the bulletins according to the given status, and if PlayableStatus is left blank, it retrieves all the bulletins. It is an IsAuthentificated request, open to anyone who is logged in.



#   UserController
    /users/change/password
-> method name: changePassword(@RequestParam String newPassword)    <p>
This request updates the password of the user logged into the API. It is an IsAuthentificated request, open to anyone who is logged in.

    /users/changedrole
-> method name: changedRole(@RequestParam String userId, @RequestParam UserRole userRole)   <p>
This request updates the role of the user with the given ID and new role. It is an OnlyAdmin request, only open to admins.

    /users
-> method name: getAllUsers(Pageable pageable)  <p>
This request retrieves all registered users. It is an OnlyAdmin request, only open to admins.

    /users/betrounds
-> method name: getAllUserBets(Pageable pageable)   <p>
This request retrieves the coupons of the logged-in user. It is an IsAuthentificated request, open to anyone who is logged in.


    