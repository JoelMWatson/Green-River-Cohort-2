Observations = new Mongo.Collection('observations');

Router.route('/', function() {
    this.render('beehive');
    this.layout('layout');
});

Router.route('/admin', function() {
    this.render('admin');
    this.layout('layout');
});

Router.route('/hive/:name', function() {
    this.render('hive', {
        data: function() {
            var currentObs = this.params.name;
            return Observations.find({name: currentObs}, {sort: {createdOn: -1}}) || {};
        }
    });
    this.layout("layout");
}, {
    name: "hive.show"
});

Router.route('/excel', function(){
    this.render('excel');
    this.layout('layout');
});

if (Meteor.isClient) {
Meteor.subscribe("observations");
    
  Template.beehive.helpers({
      'observations':function() {
          return Observations.find({}, {sort: {createdOn: -1}}) || {};
      }
  });
    
    Template.beehive.events({
      'submit form':function(event) {
          event.preventDefault();
          var hiveNameBox=$(event.target).find('input[name=hiveName]');
          var hiveNameText = hiveNameBox.val();
          var obsDateBox=$(event.target).find('input[name=obsDate]');
          var obsDateText = obsDateBox.val();
          var durationBox=$(event.target).find('input[name=duration]');
          var durationText = durationBox.val();
          var miteCountBox=$(event.target).find('input[name=miteCount]');
          var miteCountText = miteCountBox.val();
          if (hiveNameText==null|| hiveNameText==""|| obsDateText==null|| obsDateText==""|| durationText==null|| durationText==""|| miteCountText==null|| miteCountText=="")
              {
                  alert('All fields must be filled before submition.');
                  return;
              }
          else if(durationText < 0|| miteCountText < 0)
              {
                  alert('Duration and Mite count must be atleast zero.');
                  return;
              }
          Observations.insert({name: hiveNameText, observation: obsDateText, duration: durationText, count: miteCountText, createdOn: Date.now()});
         Router.go('/hive/'+hiveNameText);
      } 
  });
    
    Template.admin.helpers({
      'observations':function() {
          return Observations.find({}, {sort: {createdOn: -1}}) || {};
      }
  });
    
    Template.admin.events({
       'click #fileExport':function(event) {
           window.open('data:application/vnd.ms-excel,' + $('#tbl').html());
           event.preventDefault();
           Router.go('/excel');
       } 
    });
    
}

if (Meteor.isServer) {
    Meteor.publish("observations", function() {
        return Observations.find();
    });
}