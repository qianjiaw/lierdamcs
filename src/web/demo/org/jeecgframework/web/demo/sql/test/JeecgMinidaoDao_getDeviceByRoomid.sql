select d.id,d.name
from z_room r
join z_ddc_rfbp p
on p.roomid=r.id
join z_device d
on d.ddcId=p.ddcid
where r.id='${roomid}'