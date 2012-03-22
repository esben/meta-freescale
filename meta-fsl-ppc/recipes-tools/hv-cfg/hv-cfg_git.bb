DESCRIPTION = "Hypervisor Config"
SECTION = "hv-cfg"
LICENSE = "BSD"
PR = "r4"

LIC_FILES_CHKSUM = " \
	file://p2041rdb/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p3041ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p3060qds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p4080ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p5020ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
"

DEPENDS += "dtc-native"

# this package is specific to the machine itself
INHIBIT_DEFAULT_DEPS = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

SRCREV = "${AUTOREV}"
SRC_URI = "git://git.freescale.com/ppc/sdk/hv-cfg.git"

S = "${WORKDIR}/git"

do_deploy () {
	make install

	M=`echo ${MACHINE} | sed s/-64b//g`
	install -d ${DEPLOYDIR}/hv-cfg
	cp -r ${S}/${M}/${M}/* ${DEPLOYDIR}/hv-cfg
}
addtask deploy after do_install

ALLOW_EMPTY_${PN} = "1"
