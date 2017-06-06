select d.id,d.ddcmac
from z_ddc_rfbp r
join z_ddc d
on d.ddcmac=r.ddcmac
join z_room m
on m.id=r.roomid
where m.id=:roomid