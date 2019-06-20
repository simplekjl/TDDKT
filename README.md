# CircleCI
[![CircleCI](https://circleci.com/gh/simplekjl/TDDKT/tree/master.svg?style=svg)](https://circleci.com/gh/simplekjl/TDDKT/tree/master)

### Features

- Users Screen
- Post Screen
- Images Screen
- Testing following the rule 70, 20, 10.
- MVVM architecture pattern.
- Implementing SOLID principles and Clean architecture  ( in Progress ) 

# Let's practice TDD 

![](https://api.adorable.io/avatars/285/abott@adorable.png)



# Architecture

I used the new features such as LiveData to be aware of the events once the network gives a response back, future work would be add persistance libraries such as Room and provide a offline experience.

- Fragments
  Dummy views mostly, the current API that provides data doesn't contain great details for the objects this is why is full of   Lorem Ipsum, 
  * Base Fragment contains the common functionalities among the fragment such as show progress bar, hide progress bar and         show/ hide recycler view
  
- KOIN 
  KOIN is implemented for dependency injection

- MainViewModel 
  Recieves the data coming from the Repository, if transformations are need it this is a great place to make them ( business    logic )
  
- Repository 
  Responsible of calling the specific method required for the view model, in this case I had implemented just one scenario where I have a `Maybe.concat(...)` in order to get the value from cache or network whatever come first will be serve to the user.

- Adapters 
Used of the ViewHolder pattern to populate views for Posts, Users, Comments, AlbumImage.


# TDD

The technique TDD was used to develop some of the features.
- Arrange
- Act
- Assert
- UI Tests: 10% ( In Progress ) 
- Integration Tests: 20% ( In Progress) 
- Unit Tests: 70%  (90% complete) 



# ICONS PROVIDED BY FONT AWESOME

Link to the license
https://fontawesome.com/license
