#!/bin/sh

set -e 

basedir=$(cd $(dirname $0); pwd)

RUBY=/usr/local/rvm/rubies/ruby-2.1.1/bin/ruby
GRM_SCRIPT_FILE=$basedir/scripts/gfm.rb

if [ ! -f $RUBY ]; then
	RUBY=/c/dev/ruby-2.1.3/bin/ruby.exe
fi


if [ ! -d "$basedir/target/site" ]; then
    mkdir -p $basedir/target/site
fi
if [ ! -e "$basedir/target/site/assets" ]; then
    ln -s $basedir/../assets $basedir/target/site/assets
fi

markdown_to_html() {
    if [ ! -d $2 ]; then
        mkdir -p $2
    fi

    for file in $1/*.md; do
        echo Processing: $file
        name=$(basename $file)
        target=$2/${name%.*}.html
        $RUBY -Ku $GRM_SCRIPT_FILE $file > $target
    done
}

#markdown_to_html $basedir/docs/jetbrick-webmvc $basedir/target/html/jetbrick-webmvc

markdown_to_html $basedir/docs/jetbrick-template/1x $basedir/target/html/jetbrick-template/1x
markdown_to_html $basedir/docs/jetbrick-template/2x $basedir/target/html/jetbrick-template/2x

#mvn test -f $basedir/pom.xml

