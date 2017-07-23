// Load Grunt
module.exports = function (grunt) {
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),

    concat: {
       dist: {
         src: [
           'public/sass/*.scss',
         ],
         dest: 'public/sass/build.scss',
       }
     },
    // Tasks
    sass: { // Begin Sass Plugin
      dist: {
        files: {
            'public/css/style.css': 'public/sass/build.scss'
         }
       }
    },
   
    watch: { // Compile everything into one task with Watch Plugin
      css: {
        files: 'public/sass/**/*.{scss,sass}',
        tasks: ['concat','sass']
      },
     
    }
  });
  // Load Grunt plugins
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-watch');

  // Register Grunt tasks
  grunt.registerTask('default', 'watch');
};