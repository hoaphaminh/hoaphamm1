package vn.hoapm.springboot.domain.shared;

import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
public class AuditLog {

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
    private Timestamp uTimestamp;


    public AuditLog() {

    }

    public AuditLog withCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AuditLog withUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public AuditLog withDeletedAt(OffsetDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public AuditLog withUTimestamp(Timestamp uTimestamp) {
        this.uTimestamp = uTimestamp;
        return this;
    }

}
