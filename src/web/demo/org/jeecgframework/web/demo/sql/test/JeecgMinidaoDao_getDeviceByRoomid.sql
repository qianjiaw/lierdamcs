select d.id,d.name
from z_room r
join z_ddc_rfbp p
on p.roomid=r.id
join z_ddc ddc
on ddc.ddcmac=p.ddcmac
join z_device d
on d.ddcId=ddc.ddcid
where r.id=:roomid