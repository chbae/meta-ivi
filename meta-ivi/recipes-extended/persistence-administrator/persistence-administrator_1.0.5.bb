SUMMARY = "Administrator application for persistence"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRCREV = "68016b6762f9cbd87a7196df26d2b6fc8fcba2c1"
SRC_URI = " \
    git://git.projects.genivi.org/persistence/persistence-administrator.git;protocol=http \
    file://libsystemd_daemon.patch \
    file://0001-Add-support-for-json-c-0.12.patch \
    "
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 dbus dlt-daemon libarchive zlib json-c node-state-manager \
           systemd persistence-common-object libffi libitzam"

inherit autotools-brokensep systemd pkgconfig

FILES_${PN}-dev += "${datadir}/dbus-1/"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "pas-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_install_append() {
   perl -pi -e 's/dbus-public-bus.service/dbus.service/' \
	${D}/lib/systemd/system/pas-daemon.service
   rm -f ${D}${bindir}/pers_admin_test_framework
}
