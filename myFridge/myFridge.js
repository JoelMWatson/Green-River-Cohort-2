Products = new Mongo.Collection('products');

if (Meteor.isClient) {
    
 Template.fridge.helpers ({
     products: function () {
         return Products.find({
             place: 'fridge'
         });
     }
 });
 
 Template.productList.helpers ({
     products: function () {
         return Products.find({
             place: 'supermarket'
         });
     }
 });
 
 Template.fridge.onRendered(function() {
     var templateInstance = this;
     
     templateInstance.$('#fridge').droppable({
         drop: function(evt, ui) {
             //creating query
             var query = {_id: ui.draggable.data('id') };
             //assinging data
             var changes = {$set: {place: 'fridge'} };
             //use query
             Products.update(query, changes);
         }
     });
 });
 
 Template.productList.onRendered(function() {
     var templateInstance = this;
     
     templateInstance.$('#supermarket').droppable({
         drop: function(evt, ui) {
             //creating query
             var query = { _id: ui.draggable.data('id') };
             //assign data
             var changes = {$set: {place: 'supermarket'} };
             //use query
             Products.update(query, changes);
         }
     });
 });
 
 Template.productListItem.onRendered(function() {
     var templateInstance = this;
     
     templateInstance.$('.draggable').draggable({
         cursor: 'move',
         helper: 'clone'
     });
 });
}

if (Meteor.isServer) {
  Meteor.startup(function () {
      Products.remove({});
      
      //fill the database with some products
      Products.insert({
          name: 'Milk',
          img: '/milk.png',
          place: 'supermarket'
      });
      
      Products.insert({
          name:'Juice',
          img: '/juice.png',
          place: "fridge"
      });
      
      Products.insert({
          name:'Bread',
          img: '/bread.png',
          place: "fridge"
      });
      
      Products.insert({
          name:'Banana',
          img: '/banana.png',
          place: "supermarket"
      });
  })
}
