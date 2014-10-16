#!/bin/sh

set -e 

basedir=$(cd $(dirname $0); pwd)

RUBY=/usr/local/rvm/rubies/ruby-2.1.1/bin/ruby
GRM_SCRIPT_FILE=$basedir/scripts/gfm.rb

markdown_build() {
    if [ ! -d $2 ]; then
        mkdir -p $2
    fi

    for file in "$1/*.md"; do
        echo Processing: $file
        target=$2/$(basename $file).html
        $RUBY -Ku $GRM_SCRIPT_FILE $file > $target
    done
}

markdown_build $basedir/docs/jetbrick-webmvc $basedir/target/html/jetbrick-webmvc

#markdown_build $basedir/docs/jetbrick-template/1.x $basedir/target/html/jetbrick-template/1.x
#markdown_build $basedir/docs/jetbrick-template/2.x $basedir/target/html/jetbrick-template/2.x

#mvn test -f $basedir/pom.xml

