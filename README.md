### Features

- Show Posts.
- Show Comments by Post.
- Two panel view.
- Testing  following the rule 70, 20, 10.
- MVVM architecture pattern.

# Posts Code Challenge

![](https://api.adorable.io/avatars/285/abott@adorable.png)


# Screen shoots 
![Alt text](app/src/screenshots/Posts.jpg?raw=true "Post Screen")

*Post Screen*

![](app/src/screenshots/COmments.jpg?raw=true "Comments Screen")

*Comments Screen*

![](app/src/screenshots/MasterDetail.jpg?traw=true "Master Detail")

*Master Detail View*

# Architecture

I used the new features such as LiveData to be aware of the events once the network gives a response back, future work would be add persistance libraries such as Room and provide a offline experience.

- Fragment 
  Dummy views mostly, communications listeners are needed for communications.
Future work would be implement a MainUIModel which can define a state data and listen to this interface once the values are updated.
  
- MainViewModel 
Recieves the data coming from the Repository, if transformations are need it this is a great place to make them.
  
- Repository 
In charge of making service calls and a small cache system, the example of the `User.username ` for every post is taken with this strategy, the `userId` is verified in the cache system and if it exists we provide the data stored already preventing a network call.

- Adapters 
Used of the ViewHolder pattern to populate views for Posts, Users, Comments.

- Fragments
Use of Fragments to allow flexibility for showing different types of views. 
Further work would be to show the UserProfileFragment with its related post in a master detail view.

# The use off TDD as part of the development of the features.

The technique TDD was used to develop some of the features.
- Arrange
- Act
- Assert
- UI Tests: 10% Integration Tests: 20% Unit Tests: 70%

# CircleCI
[![CircleCI](https://circleci.com/gh/simplekjl/TDDKT/tree/master.svg?style=svg)](https://circleci.com/gh/simplekjl/TDDKT/tree/master)


# ICONS PROVIDED BY FONT AWESOME

Link to the license
https://fontawesome.com/license
