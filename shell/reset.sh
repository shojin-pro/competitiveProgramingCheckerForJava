#!/bin/bash
rm ../in/*.txt 2> /dev/null
rmdir ../in 2> /dev/null
rm ../out/*.txt 2> /dev/null
rmdir ../out 2> /dev/null
rm ../correct/*.txt 2> /dev/null
rmdir ../correct 2> /dev/null
rm ../log/*.log* 2> /dev/null
rmdir ../log 2> /dev/null
exec $(mkdir ../in)
exec $(mkdir ../out)
exec $(mkdir ../correct)
exec $(mkdir ../log)