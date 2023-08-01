# Tournament-Bracket
Tournament Bracket
Bracket with Bets and Predicitions
This application will be able to create tournament brackets which can also include predictions and a prize pool.

This Application is for any person with the intention of hosting competitions or tournaments, no matter the size of the event.

This project interests me as I always found placing bets on events exciting and fun (within reason). I have also played a lot of games and watched a couple sports where a tournament bracket is used, showing the wide range of uses this application can have. I hope this application can help simplify or even improve event planning for local clubs or events.

User Stories
As a user, I want to display the list of teams currently participating in the tournament.
As a user, I want to be able to add multiple teams to a tournament.
As a user, I want the ability to add bids and bets (or predictions) on which team will win.
As a user, I want the ability to edit names, bids and bets (or predictions).
As a user, I want to display an editable prize pool.
As a user, I want to save my tournaments progress.
As a user, I want to be able to view results from previous tournaments.

Although my program works, had I been given more time, I would have refactored the GUI. The current GUI has one class handling too many things, causing any wanted change to be quite troublesome to implement, such as loading a new Tournament into the GUI. To refactor, I could have

Made TournamentGUI act more as a controller, and have another class act as the JFrame.
Been more graceful with the way I was retrieving data and updating certain variables.
