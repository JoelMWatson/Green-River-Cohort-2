//create database
Messages = new Mongo.Collection('messages');

Router.route('/', function() {
   this.render('about');
   this.layout('loggedOutLayout');
});

Router.route('/profile', function () {
   this.render('profile');
   this.layout('loggedInLayout');
});

Router.route('/directory', function() {
   this.render('directory');
   this.layout('loggedInLayout');
});

Router.route('/profile/:_id', function () {
     var userId = this.params._id;
    Session.set('recipientId', userId);
    
    this.render('specificProfile', {
        data: function() {
            var userId = this.params._id;
            return Messages.find({recipient: userId}, {sort: {createdOn: -1}}) || {};
        }
    });
    this.layout('loggedInLayout');
    
});

if (Meteor.isClient) {
  Meteor.subscribe("messages");
  Meteor.subscribe("userList");
    
  Accounts.ui.config({
    passwordSignupFields: "USERNAME_ONLY"
  });
    
    Template.navigator.events({
       'click #userProfile':function(event) {
           event.preventDefault();
           var userId = Meteor.userId();
           var url="/profile/" + userId;
           Router.go(url);
       } 
    });
    
     Template.specificProfile.events({
      'submit form':function(event) {
          event.preventDefault();
          var messageBox=$(event.target).find('textarea[name=message]');
          var messageText = messageBox.val();
          var name = Meteor.user().username;
          var id = Meteor.userId();
          var recipient = Session.get('recipientId');
          Messages.insert({message: messageText, name: name, id: id, recipient: recipient, createdOn: Date.now()});
          messageBox.val(" ");
      }
  });
    
 
    
  Template.specificProfile.helpers({
     'usersName' :function() {
         var recipient = Session.get('recipientId');
         return Meteor.users.findOne({_id: recipient});
     }
      
  });
    
  Template.directory.helpers({
     'messages':function() {
         return Messages.find({_id: this.params._id});
     }, 
     'userList':function() {
         return Meteor.users.find({}, {sort: {createdAt: -1}});
     } 
  });
    
}

if (Meteor.isServer) {
  Meteor.publish("messages", function() {
        return Messages.find();    
  });
    
  Meteor.publish("userList", function () {
        return Meteor.users.find({});
  });
    
  Messages.allow({
    insert: function () { return true; },
    update: function () { return true; },
    remove: function () { return true; }
  });
  
}

