####### set the classpath delimiters ##########
if [ -z $D ] ; then
	D=":"
fi

####### set the libs ############
if [ -z $FIND ] ; then
	export FIND="find"
fi

####### set the cli location #######
CLIPACK=net.hamtag.server.cli
ROOTTOLIB=../lib

CLIPACK=${CLIPACK}.

####### finding the libs ###########
CLIROOT=`echo -n "." ; echo ${CLIPACK} |
	while read -n 1 c ; do
		if [ "$c" = "."  ] ; then
			echo -n "/.."
		fi
	done`

export LIB=${CLIROOT}/${ROOTTOLIB}
export LIBS=`${FIND} ${LIB} | 
	grep jar | 
	grep -v "\/\.svn\/" |
	while read j ; do
		echo -n "$D$j"
	done`
####### set the classpath ###########
CP=${CLIROOT}${LIBS}

####### set the log4j.properties file ####
CLIPATH=`echo $CLIPACK | sed s/\\\./\\\//g`
#LOG4JPROP=${CLIPATH}log4j.properties 
#LOG4JCONFIG=-Dlog4j.configuration=${LOG4JPROP}


####### run the application #####
#java -Xms256m -Xmx7500m ${LOG4JCONFIG} -cp ${CP} ${CLIPACK}$*
java -Xms256m -Xmx7500m -cp ${CP} ${CLIPACK}$*
#